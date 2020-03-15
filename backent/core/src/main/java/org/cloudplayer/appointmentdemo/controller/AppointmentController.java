package org.cloudplayer.appointmentdemo.controller;

import org.cloudplayer.appointmentdemo.domain.Appointment;
import org.cloudplayer.appointmentdemo.domain.Round;
import org.cloudplayer.appointmentdemo.domain.Success;
import org.cloudplayer.appointmentdemo.mapper.AppointMapper;
import org.cloudplayer.appointmentdemo.utils.Encode;
import org.cloudplayer.appointmentdemo.utils.JsonUtil;
import org.cloudplayer.appointmentdemo.utils.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {

	@Autowired
	private AppointMapper appointMapper;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> test() {
		Map<String, Object> data  = new HashMap<>();
		data.put("code", 200);
		data.put("msg", "success");

		return ResponseEntity.ok(data);
	}

	@GetMapping("/can_appointment")
	public ResponseEntity<Map<String, Object>> canAppoint() {
//		String date = new Date(System.currentTimeMillis());
		Round round = appointMapper.findLatestRound();
		Map<String, Object> data = new HashMap<>();
		if (round.isOver()) {
			data.put("status", false);
		} else {
			data.put("status", true);
			data.put("round", round);
		}

		return ResponseEntity.ok(JsonUtil.success(data));
	}


	/**
	 * 创建新预约信息
	 * @param appointment
	 * @return
	 */
	@PostMapping("/appointment/create")
	public ResponseEntity<Map<String, Object>> appoint(@RequestBody Appointment appointment) {

		String serial = Encode.MD5(appointment.getName()+appointment.getPhone()+appointment.getIdNumber()+appointment.getRound());
		appointment.setSerial(serial);
		List<Success> successList = appointMapper.findSuccessByIdentify(appointment.getIdNumber());
		for (Success success : successList) {
			if (appointment.getRound() - success.getRound() <= 3)
				return ResponseEntity.ok(JsonUtil.fail("前三轮之内已成功过,请等待三轮后再预约", ResCode.FORBIDDEN.getCode()));
		}
		Appointment check = appointMapper.findAppointmentByIdentify(appointment.getIdNumber(), appointment.getRound());
		if (check != null) {
			return ResponseEntity.ok(JsonUtil.fail("已预约,请耐心等待", ResCode.FORBIDDEN.getCode()));
		}
		Round round = appointMapper.findLatestRound();
		if (round.isOver() && round.getId() == appointment.getRound()) {
			return ResponseEntity.ok(JsonUtil.fail("此轮预约已结束,请下次再来", ResCode.NOT_FOUND.getCode()));
		}
		appointMapper.insertRecord(appointment);

		HashMap<String, Object> data = new HashMap<>();
		data.put("serialNumber", serial);
		return ResponseEntity.ok(JsonUtil.success(data));
	}

	@GetMapping("/appointment")
	public ResponseEntity<Map<String, Object>> check(@RequestParam("serialNumber")String serialNumber) {
		Round round = appointMapper.findLatestRound();
		if (!round.isOver()) {
			return ResponseEntity.ok(JsonUtil.fail("此轮预约还未结束,请结束后再来查询", ResCode.FORBIDDEN.getCode()));
		}

		Success success = appointMapper.findSuccessBySerial(serialNumber);
		if (success == null) {
			return ResponseEntity.ok(JsonUtil.fail("很抱歉这次您没有中签", ResCode.NOT_FOUND.getCode()));
		}

		Map<String, Object> data = new HashMap<>();
		Appointment appointment = appointMapper.findAppointmentBySerial(serialNumber);
		appointment.setSerial(serialNumber);

		return ResponseEntity.ok(JsonUtil.success(data));

	}

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(date);
		Date newDate = format.parse("2020-03-16");
		System.out.println(format.format(newDate));
	}
}

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {

	@Autowired
	private AppointMapper appointMapper;

	public AppointmentController() {
	}

	@RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
	public ResponseEntity<Map<String, Object>> test() {
		Map<String, Object> data = new HashMap();
		data.put("code", 200);
		data.put("msg", "success");
		return ResponseEntity.ok(data);
	}

	@GetMapping({"/can_appointment"})
	public ResponseEntity<Map<String, Object>> canAppoint() {
		Round round = this.appointMapper.findLatestRound();
		Map<String, Object> data = new HashMap();
		if (round.isOver()) {
			data.put("status", false);
		} else {
			data.put("status", true);
			data.put("round", round);
		}

		return ResponseEntity.ok(JsonUtil.success(data));
	}

	@PostMapping({"/appointment/create"})
	public ResponseEntity<Map<String, Object>> appoint(@RequestBody Appointment appointment) {
		String serial = Encode.MD5(appointment.getName() + appointment.getPhone() + appointment.getIdNumber() + appointment.getRound());
		appointment.setSerial(serial);
		List<Success> successList = this.appointMapper.findSuccessByIdentify(appointment.getIdNumber());
		Iterator var4 = successList.iterator();

		Success success;
		do {
			if (!var4.hasNext()) {
				Appointment check = this.appointMapper.findAppointmentByIdentify(appointment.getIdNumber(), appointment.getRound());
				if (check != null) {
					return ResponseEntity.ok(JsonUtil.fail("已预约,请耐心等待", ResCode.FORBIDDEN.getCode()));
				}

				Round round = this.appointMapper.findLatestRound();
				if (round.isOver() && round.getId() == appointment.getRound()) {
					return ResponseEntity.ok(JsonUtil.fail("此轮预约已结束,请下次再来", ResCode.NOT_FOUND.getCode()));
				}

				this.appointMapper.insertRecord(appointment);
				HashMap<String, Object> data = new HashMap();
				data.put("serialNumber", serial);
				return ResponseEntity.ok(JsonUtil.success(data));
			}

			success = (Success)var4.next();
		} while(appointment.getRound() - success.getRound() > 3);

		return ResponseEntity.ok(JsonUtil.fail("前三轮之内已成功过,请等待三轮后再预约", ResCode.FORBIDDEN.getCode()));
	}

	@GetMapping({"/appointment"})
	public ResponseEntity<Map<String, Object>> check(@RequestParam("serialNumber") String serialNumber) {
		Round round = this.appointMapper.findLatestRound();
		if (!round.isOver()) {
			return ResponseEntity.ok(JsonUtil.fail("此轮预约还未结束,请结束后再来查询", ResCode.FORBIDDEN.getCode()));
		} else {
			Success success = this.appointMapper.findSuccessBySerial(serialNumber);
			if (success == null) {
				return ResponseEntity.ok(JsonUtil.fail("很抱歉这次您没有中将", ResCode.NOT_FOUND.getCode()));
			} else {
				Map<String, Object> data = new HashMap();
				Appointment appointment = this.appointMapper.findAppointmentBySerial(serialNumber);
				appointment.setSerial(serialNumber);
				return ResponseEntity.ok(JsonUtil.success(data));
			}
		}
	}
}

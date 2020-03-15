package org.cloudplayer.appointmentdemo.controller;

import org.cloudplayer.appointmentdemo.domain.Appointment;
import org.cloudplayer.appointmentdemo.domain.Round;
import org.cloudplayer.appointmentdemo.mapper.AppointMapper;
import org.cloudplayer.appointmentdemo.utils.JsonUtil;
import org.cloudplayer.appointmentdemo.utils.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class TestController {

	@Autowired
	private AppointMapper appointMapper;

	@GetMapping("/test/start")
	public ResponseEntity<Map<String, Object>> startAppointment() {
		if (appointMapper.findLatestRound().getEnd() != null) {
			Round round = new Round();
			appointMapper.insertNewRound(round);

			return ResponseEntity.ok(JsonUtil.success());

		} else {
			return ResponseEntity.ok(JsonUtil.fail("上一轮仍未结束",ResCode.FORBIDDEN.getCode()));
		}
	}

	@GetMapping("/test/end")
	public ResponseEntity<Map<String, Object>> endAppointment() {
		if (appointMapper.findLatestRound().getEnd() != null) {
			return ResponseEntity.ok(JsonUtil.fail("上一轮已结束",ResCode.FORBIDDEN.getCode()));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Round round = appointMapper.findLatestRound();
		round.setEnd(sdf.format(new Date()));
		appointMapper.endRound(round);
		//设置中奖 测试中随机生成
		List<Appointment> appointments = appointMapper.findAppointmentByRound(round.getId());

		Random rand = new Random();
		for (Appointment a : appointments) {
			if (rand.nextBoolean()) {
				appointMapper.insertSuccess(a.getIdNumber(), a.getSerial(), a.getRound());
			}
		}

		return ResponseEntity.ok(JsonUtil.success());
	}
}

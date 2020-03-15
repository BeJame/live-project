
package org.cloudplayer.appointmentdemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.cloudplayer.appointmentdemo.domain.Appointment;
import org.cloudplayer.appointmentdemo.domain.Round;
import org.cloudplayer.appointmentdemo.mapper.AppointMapper;
import org.cloudplayer.appointmentdemo.utils.JsonUtil;
import org.cloudplayer.appointmentdemo.utils.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	private AppointMapper appointMapper;

	public TestController() {
	}

	@GetMapping({"/test/start"})
	public ResponseEntity<Map<String, Object>> startAppointment() {
		if (this.appointMapper.findLatestRound().getEnd() != null) {
			Round round = new Round();
			this.appointMapper.insertNewRound(round);
			return ResponseEntity.ok(JsonUtil.success());
		} else {
			return ResponseEntity.ok(JsonUtil.fail("上一轮仍未结束", ResCode.FORBIDDEN.getCode()));
		}
	}

	@GetMapping({"/test/end"})
	public ResponseEntity<Map<String, Object>> endAppointment() {
		if (this.appointMapper.findLatestRound().getEnd() != null) {
			return ResponseEntity.ok(JsonUtil.fail("上一轮已结束", ResCode.FORBIDDEN.getCode()));
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Round round = this.appointMapper.findLatestRound();
			round.setEnd(sdf.format(new Date()));
			this.appointMapper.endRound(round);
			List<Appointment> appointments = this.appointMapper.findAppointmentByRound(round.getId());
			Random rand = new Random();
			Iterator var5 = appointments.iterator();

			while(var5.hasNext()) {
				Appointment a = (Appointment)var5.next();
				if (rand.nextBoolean()) {
					this.appointMapper.insertSuccess(a.getIdNumber(), a.getSerial(), a.getRound());
				}
			}

			return ResponseEntity.ok(JsonUtil.success());
		}
	}
}

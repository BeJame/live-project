package org.cloudplayer.appointmentdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppointmentController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> test() {
		Map<String, Object> data  = new HashMap<>();
		data.put("code", 200);
		data.put("msg", "success");

		return ResponseEntity.ok(data);
	}
}

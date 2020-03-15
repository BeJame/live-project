package org.cloudplayer.appointmentdemo.mapper;


import org.apache.ibatis.annotations.*;
import org.cloudplayer.appointmentdemo.domain.Appointment;
import org.cloudplayer.appointmentdemo.domain.Round;
import org.cloudplayer.appointmentdemo.domain.Success;

import java.util.List;

public interface AppointMapper  {


	@Insert("INSERT INTO t_record(re_identify,re_name,re_phone,re_amount,re_serial,re_ap_id) " +
			"VALUES(#{idNumber},#{name},#{phone},#{amount},#{serial},#{round})")
	void insertRecord(Appointment appointment);


	@Select("SELECT * FROM t_success WHERE su_serial=#{param1}")
	@Results(id = "successMapper", value = {
			@Result(column = "su_identify", property = "id"),
			@Result(column = "su_serial", property = "serial"),
			@Result(column = "su_ap_id", property = "round")
	})
	Success findSuccessBySerial(String serial);

	@Select("SELECT * FROM t_success WHERE su_identify=#{param1}")
	@ResultMap(value = "successMapper")
	List<Success> findSuccessByIdentify(String id);

	@Select("SELECT * FROM t_appointment ORDER BY ap_id DESC LIMIT 1")
	@Results(id = "roundMapper", value = {
			@Result(property = "id", column = "ap_id"),
			@Result(property = "name", column = "ap_name"),
			@Result(property = "begin", column = "ap_begin"),
			@Result(property = "end", column = "ap_end"),
			@Result(property = "over", column = "ap_over")
	})
	Round findLatestRound();

	@Insert("INSERT INTO t_appointment(ap_name,ap_begin) " +
			"VALUES(#{name},#{begin})")
	void insertNewRound(Round round);

	@Update("UPDATE t_appointment SET ap_end=#{end}, ap_over=1 WHERE ap_id=#{id}")
	void endRound(Round round);


	@Select("SELECT * FROM t_record WHERE re_serial=#{param1}")
	@Results(id = "appointmentMapper", value = {
			@Result(property = "round", column = "re_ap_id"),
			@Result(property = "name", column = "re_name"),
			@Result(property = "idNumber", column = "re_identify"),
			@Result(property = "phone", column = "re_phone"),
			@Result(property = "amount", column = "re_amount"),
			@Result(property = "serial", column = "re_serial")
	})
	Appointment findAppointmentBySerial(String serial);

	@Select("SELECT * FROM t_record WHERE re_identify=#{param1} AND re_ap_id=#{param2}")
	@ResultMap(value = "appointmentMapper")
	Appointment findAppointmentByIdentify(String identify, int round);

	@Select("SELECT * FROM t_record WHERE re_ap_id=#{param1}")
	@ResultMap(value = "appointmentMapper")
	List<Appointment> findAppointmentByRound(int round);


	@Insert("INSERT INTO t_success(su_identify, su_serial, su_ap_id) " +
			"VALUES(#{param1}, #{param2}, #{param3})")
	void insertSuccess(String id, String serial, int apId);
}

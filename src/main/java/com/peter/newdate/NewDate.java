package com.peter.newdate;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/12
 * @Description: 新时间日期
 * @modifier
 */
public class NewDate {

	/**
	 * 1. LocalDate、LocalTime、LocalDateTime
	 * 用法一样
	 */
	@Test
	public void test1() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		LocalDateTime ldt2 = LocalDateTime.of(2020,1,1,1,1,1);
		System.out.println(ldt2);

		ldt2.plusYears(1);
		System.out.println(ldt2);

		System.out.println(ldt2.getYear());
		System.out.println(ldt2.getHour());
		System.out.println(ldt2.getSecond());
	}

	/**
	 * 2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
	 */
	@Test
	public void test2() {
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println(instant.toEpochMilli());
	}

	/**
	 * 3
	 * Duration : 用于计算两个“时间”间隔
	 * Period : 用于计算两个“日期”间隔
	 */
	@Test
	public void test3() {
		Instant ins1 = Instant.now();

		System.out.println("--------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		Instant ins2 = Instant.now();

		System.out.println("所耗费时间为：" + Duration.between(ins1, ins2));

		System.out.println("----------------------------------");

		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2011, 1, 1);

		Period pe = Period.between(ld2, ld1);
		System.out.println(pe.getYears());
		System.out.println(pe.getMonths());
		System.out.println(pe.getDays());

	}

	/**
	 * 4.TemporalAdjuster : 时间校正器
	 */
	@Test
	public void test4() {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime ldt2 = localDateTime.withDayOfMonth(5); //取该月份第五天
		System.out.println(ldt2);

		LocalDateTime ldt3 = localDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println(ldt2);

		//自定义下一个工作日
		LocalDateTime ldt4 = localDateTime.with(l -> {
			DayOfWeek dow = ((LocalDateTime)l).getDayOfWeek();
			if(dow.equals(DayOfWeek.FRIDAY)) {
				((LocalDateTime) l).plusDays(3);
			}else if(dow.equals(DayOfWeek.SATURDAY)) {
				((LocalDateTime) l).plusDays(2);
			}else if(dow.equals(DayOfWeek.SUNDAY)) {
				((LocalDateTime) l).plusDays(1);
			}
			return l;
		});
		System.out.println(ldt4);
	}

	/**
	 * 5. DateTimeFormatter : 解析和格式化日期或时间
	 */
	@Test
	public void test5() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.now();
		String ldtStr = ldt.format(dtf);
		System.out.println(ldtStr);

		LocalDateTime origin = ldt.parse(ldtStr,dtf);
		System.out.println(origin);
	}

	/**
	 * 6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
	 */
	@Test
	public void test6() {
		//所有可用是去
		ZoneId.getAvailableZoneIds()
				.forEach(System.out::println);

		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(ldt);
	}
}

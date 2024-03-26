package com.alabi.app.timestamp;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TimestampGenerator {
	
		/*
		* Generates timestamp in milliseconds between the given minimum 
		* and maximum values inclusive
		* @Param(long min: minimum minutes ago, long max: maximum minutes ago)
		*/
		 public long generateTimeStampRange(long min, long max) {
			 long currentTime = System.currentTimeMillis();
			 Random random = new Random();
			 long randomOffset = ((min + 1) + random.nextInt(4)) * 60 * (max) * 100;// 6 to 9 mins
			 long randomTimeStamp = currentTime - randomOffset;
			 return randomTimeStamp;
		 }
}

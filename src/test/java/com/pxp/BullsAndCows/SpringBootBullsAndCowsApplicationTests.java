package com.pxp.BullsAndCows;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.Game;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringBootBullsAndCowsApplicationTests {

	@Test
	void contextLoads() {
		var cmb = new Combination();
		var t = new Time();
		var data = new Date();
		cmb.setTimeOfGame(data);
	}

}

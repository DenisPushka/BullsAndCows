package com.pxp.BullsAndCows;

import com.pxp.BullsAndCows.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBullsAndCowsApplicationTests {

    @Test
    void contextLoads() {
        var game = new Game();
		game.setTrueComb("1234");
		var a = game.processing("7658");
    }

}

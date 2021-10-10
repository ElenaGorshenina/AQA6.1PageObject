package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        int balance1before = dashBoardPage.getFirstCardBalance();
        int balance2before = dashBoardPage.getSecondCardBalance();
        var replenishCard1 = dashBoardPage.replenishCard1();
        String sum = "100";
        var numberCard = DataHelper.getNumberCard2();
        var replenish = replenishCard1.replenish(sum, numberCard);
        int balance1after = dashBoardPage.getFirstCardBalance();
        int balance2after = dashBoardPage.getSecondCardBalance();
        assertEquals(balance1before + Integer.parseInt(sum), balance1after);
        assertEquals(balance2before - Integer.parseInt(sum), balance2after);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        int balance1before = dashBoardPage.getFirstCardBalance();
        int balance2before = dashBoardPage.getSecondCardBalance();
        var replenishCard2 = dashBoardPage.replenishCard2();
        String sum = "300";
        var numberCard = DataHelper.getNumberCard1();
        var replenish = replenishCard2.replenish(sum, numberCard);
        int balance1after = dashBoardPage.getFirstCardBalance();
        int balance2after = dashBoardPage.getSecondCardBalance();
        assertEquals(balance1before - Integer.parseInt(sum), balance1after);
        assertEquals(balance2before + Integer.parseInt(sum), balance2after);
    }

    @Test
    void noShouldTransferMoneyIfSumMoreBalance() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        int balance1before = dashBoardPage.getFirstCardBalance();
        int balance2before = dashBoardPage.getSecondCardBalance();
        var replenishCard1 = dashBoardPage.replenishCard1();
        String sum = "15000";
        var numberCard = DataHelper.getNumberCard2();
        var replenish = replenishCard1.replenish(sum, numberCard);
        int balance1after = dashBoardPage.getFirstCardBalance();
        int balance2after = dashBoardPage.getSecondCardBalance();
        assertEquals(balance1before, balance1after);
        assertEquals(balance2before, balance2after);
    }

    @Test
    void errorIfNumberCardNoValid() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        int balance1before = dashBoardPage.getFirstCardBalance();
        int balance2before = dashBoardPage.getSecondCardBalance();
        var replenishCard1 = dashBoardPage.replenishCard1();
        String sum = "200";
        var numberCard = DataHelper.getNumberCardNoValid();
        var replenish = replenishCard1.replenish(sum, numberCard);
        var replenish1 = replenishCard1.errorCard();
        int balance1after = dashBoardPage.getFirstCardBalance();
        int balance2after = dashBoardPage.getSecondCardBalance();
        assertEquals(balance1before, balance1after);
        assertEquals(balance2before, balance2after);
    }

}

package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        var replenishCard1 = dashBoardPage.replenishCard1();
        String sum = "100";
        var numberCard = DataHelper.getNumberCard2();
        var replenish = replenishCard1.replenish(sum, numberCard);
        int balance1= dashBoardPage.getFirstCardBalance();
        int balance2= dashBoardPage.getSecondCardBalance();
        assertEquals(10100, balance1);
        assertEquals(9900, balance2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        var replenishCard2 = dashBoardPage.replenishCard2();
        String sum = "300";
        var numberCard = DataHelper.getNumberCard1();
        var replenish = replenishCard2.replenish(sum, numberCard);
        int balance1= dashBoardPage.getFirstCardBalance();
        int balance2= dashBoardPage.getSecondCardBalance();
        assertEquals(9700, balance1);
        assertEquals(10300, balance2);
    }

}

package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;

public class ReplenishCard {
    private SelenideElement headingH1 = $("h1");
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement replenishButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorWindow = $(".notification__content");

    public ReplenishCard() {
        headingH1.shouldBe(Condition.visible);
    }

    public void replenish(String sum, DataHelper.NumberCard numberCard) {
        amount.setValue(sum);
        from.setValue(numberCard.getNumberCard());
        replenishButton.click();
    }

    public DashboardPage complitReplenish(String sum, DataHelper.NumberCard numberCard) {
        replenish(sum, numberCard);
        return new DashboardPage();
    }

    public void errorCard() {
        errorWindow.shouldHave(exactText("Недействительный номер карты"));
    }

}

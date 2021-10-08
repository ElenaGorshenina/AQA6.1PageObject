package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class ReplenishCard {
    private SelenideElement headingH1 = $("h1");
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement replenishButton = $("[data-test-id='action-transfer']");

    public ReplenishCard() {
        headingH1.shouldBe(Condition.visible);
    }

    public DashboardPage replenish (String sum, DataHelper.NumberCard numberCard){
            amount.setValue(sum);
            from.setValue(numberCard.getNumberCard());
            replenishButton.click();
            return new DashboardPage();
        }

}

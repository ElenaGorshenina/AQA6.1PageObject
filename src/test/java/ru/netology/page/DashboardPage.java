package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement card1 = $$(".list__item").first();
    private SelenideElement cardButton1 = $$("[data-test-id='action-deposit']").first();
    private SelenideElement card2 = $$(".list__item").last();
    private SelenideElement cardButton2 = $$("[data-test-id='action-deposit']").last();

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public ReplenishCard replenishCard1(){
        cardButton1.click();
        return new ReplenishCard();
    }

    public ReplenishCard replenishCard2(){
        cardButton2.click();
        return new ReplenishCard();
    }

    public int getFirstCardBalance() {
        val text = card1.text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = card2.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}

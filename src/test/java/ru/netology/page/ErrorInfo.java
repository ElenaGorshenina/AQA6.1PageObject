package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class ErrorInfo {
    private static SelenideElement errorWindow = $(".notification__content");

    public ErrorInfo() {
        errorWindow.shouldBe(Condition.visible);
    }

    public static ReplenishCard errorCard(){
        errorWindow.shouldHave(exactText("Недействительный номер карты"));
        return new ReplenishCard();
    }
}

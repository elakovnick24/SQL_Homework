package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class ProcessAuth {
    @Test
    void shouldTransferMoneyBetweenOwnCards() throws SQLException {

        val loginPage = open("http://localhost:9999", LoginPage.class);
// можно заменить на val loginPage = open("http://localhost:9999", LoginPageV2.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}

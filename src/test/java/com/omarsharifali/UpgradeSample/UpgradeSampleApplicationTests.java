package com.omarsharifali.UpgradeSample;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=UpgradeSampleApplication.class)
@EnableWebMvc
@AutoConfigureMockMvc
@WebMvcTest(WalletController.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpgradeSampleApplicationTests {

	@Autowired
	private MockMvc mvc;

    @Test
    public void testA() throws Exception {
        this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Omar Sharifali")));
    }

	@Test
	public void testB() throws Exception {

        this.mvc.perform(get("/getBalance")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Wallet Not Found Exception")));
    }

    @Test
    public void testC() throws Exception {

        this.mvc.perform(get("/createWallet")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("You have created a new wallet")));
    }

    @Test
    public void testD() throws Exception {

        this.mvc.perform(get("/getBalance?account=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Account 1 has not been created!")));
    }

    @Test
    public void testE() throws Exception {

        this.mvc.perform(get("/deposit?amount=100")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("100")));
    }

    @Test
    public void testF() throws Exception {

        this.mvc.perform(get("/getBalance")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("100")));
    }

    @Test
    public void testG() throws Exception {

        this.mvc.perform(get("/deposit?amount=44")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("144")));
    }

    @Test
    public void testH() throws Exception {

        this.mvc.perform(get("/withdraw?amount=44")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("100")));
    }

    @Test
    public void testI() throws Exception {

        this.mvc.perform(get("/createAccount")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("New account added to wallet")));
    }

    @Test
    public void testJ() throws Exception {

        this.mvc.perform(get("/transfer?to=1&from=0&amount=50")).andDo(print()).andExpect(status().isOk());
        this.mvc.perform(get("/getBalance")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("50")));
        this.mvc.perform(get("/getBalance?account=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("50")));
    }

    @Test
    public void testK() throws Exception {

        this.mvc.perform(get("/getTransactions?number=3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("WITHDRAW")));
    }

    @Test
    public void testL() throws Exception {

        this.mvc.perform(get("/deposit?amount=44&account=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Deposit to Account 1 successful, new balance: 94.0")));
    }

}

package com.example.jdbc.component;

import com.example.jdbc.model.Account;
import com.example.jdbc.service.impl.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerSpringBootAnnotationTest {
    @MockBean
    private AccountServiceImpl accountService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void shouldReturnAccount() throws Exception {
        int id = 1;
        Account account = new Account(id,"test@example.com","123456");
        when(accountService.findAccountById(id)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.get("/account/{id}", 1)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(account.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(account.getPassword()))
                .andDo(print());
    }

    @Test
    public void shouldReturnNotFoundAccount() throws Exception {
        int id = 1;
        when(accountService.findAccountById(id)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/account/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldReturnListAccount() throws Exception {
        List<Account> accounts = new ArrayList<>(
        Arrays.asList(new Account(1, "user1@example.com", "123456789"),
                new Account(2, "user2@example.com", "123456789"),
                new Account(3, "user3@example.com", "123456789"),
                new Account(4, "user4@example.com", "123456789"),
                new Account(5, "user5@example.com", "123456789"),
                new Account(6, "user6@example.com", "123456789"),
                new Account(7, "user7@example.com", "123456789"),
                new Account(8, "user8@example.com", "123456789"),
                new Account(9, "user9@example.com", "123456789"),
                new Account(10, "user10@example.com", "123456789")));

        when(accountService.findAccounts()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(accounts.size()))
                .andDo(print());
    }

    @Test
    public void shouldCreateAccount() throws Exception {
        int id = 1;
        Account account = new Account(id,"test@example.com","123456");
        when(accountService.addAccount(any())).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/account").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                        .andExpect(status().isCreated())
                        .andDo(print());
    }

    @Test
    public void shouldUpdateAccount() throws Exception {
        int id = 1;

        Account account = new Account(id,"test@example.com","123456");
        Account updatedAccount = new Account(id,"update@example.com","123456");

        when(accountService.findAccountById(id)).thenReturn(account);
        when(accountService.editAccount(any())).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/account").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAccount)))
                        .andExpect(status().isOk())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedAccount.getId()))
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(updatedAccount.getEmail()))
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(updatedAccount.getPassword()))
                        .andDo(print());
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        int id = 1;
        Account account = new Account(id,"test@example.com","123456");
        when(accountService.findAccountById(id)).thenReturn(account);
        when(accountService.deleteAccount(id)).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

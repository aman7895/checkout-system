package deep.aman.checkoutsystem.controller.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void viewCartTest() {
        UserController userController = new UserController();
        assertEquals("Samsung S20", userController.viewCart().getName());
    }

    @AfterEach
    void tearDown() {
    }
}
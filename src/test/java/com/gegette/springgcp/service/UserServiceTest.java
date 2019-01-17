package com.gegette.springgcp.service;

import com.gegette.springgcp.exception.RGException;
import com.gegette.springgcp.exception.UserNotFoundException;
import com.gegette.springgcp.model.GroupDB;
import com.gegette.springgcp.model.UserDB;
import com.gegette.springgcp.repository.IUserRepository;
import com.gegette.springgcp.service.impl.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.security.InvalidParameterException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserService.class, loader = AnnotationConfigContextLoader.class)
public class UserServiceTest {

    private static final String LASTNAME = "CESARO";
    private static final String FIRSTNAME = "Gaëtan";
    private static final String MAIL = "gaetan.cesaro@mail.com";
    private static final List<GroupDB> GROUPS = null;

    @MockBean
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * GET /user
     */
    @Test
    public void test_getUser() {
        UserDB userDB = new UserDB(MAIL, LASTNAME, FIRSTNAME, GROUPS);

        Mockito.when(userRepository.findByMail(Mockito.anyString())).thenReturn(userDB);

        userDB = userService.getUser(MAIL);

        Assert.assertEquals(MAIL, userDB.getMail());
        Assert.assertEquals(LASTNAME, userDB.getLastName());
        Assert.assertEquals(FIRSTNAME, userDB.getFirstName());
        Assert.assertEquals(GROUPS, userDB.getGroups());
    }

    @Test(expected = InvalidParameterException.class)
    public void test_getDemandePrelevement_search_null() {
        userService.getUser(null);
    }

    @Test(expected = UserNotFoundException.class)
    public void test_getDemandePrelevement_inconnu() {
        userService.getUser(FIRSTNAME);
    }

    /**
     * POST /user
     * @return
     */
    @Test(expected = InvalidParameterException.class)
    public void test_postUser_user_null() throws RGException {
        userService.postUser(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_postUser_lastname_null() throws RGException {
        UserDB userDB = new UserDB(MAIL, null, FIRSTNAME, GROUPS);
        userService.postUser(userDB);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_postUser_firstname_null() throws RGException {
        UserDB userDB = new UserDB(MAIL, LASTNAME, null, GROUPS);
        userService.postUser(userDB);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_postUser_mail_null() throws RGException {
        UserDB userDB = new UserDB(null, LASTNAME, FIRSTNAME, GROUPS);
        userService.postUser(userDB);
    }

    @Test
    public void test_postUser_create() throws RGException {
        UserDB userDB = new UserDB(MAIL, LASTNAME, FIRSTNAME, GROUPS);

        Mockito.when(userRepository.save(Mockito.any(UserDB.class))).thenReturn(userDB);

        userDB = userService.postUser(userDB);

        Assert.assertEquals(MAIL, userDB.getMail());
        Assert.assertEquals(LASTNAME, userDB.getLastName());
        Assert.assertEquals(FIRSTNAME, userDB.getFirstName());
        Assert.assertEquals(GROUPS, userDB.getGroups());
    }

}

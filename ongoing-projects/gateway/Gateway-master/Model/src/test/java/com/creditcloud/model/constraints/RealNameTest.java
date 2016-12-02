/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.constraints;

import com.creditcloud.model.user.User;
import static com.creditcloud.model.constraints.BaseTest.validator;
import com.creditcloud.model.enums.Source;
import javax.validation.Validation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sobranie
 */
public class RealNameTest extends BaseTest<User> {

    @BeforeClass
    public static void setUpClass() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        object = new User("123", "123", "123", "123", "123", "123", "123", Source.WEB, null, null);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canNull() {
        object.setName(null);
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void test() {
        object.setName("陈忞");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());

        object.setName("朱镕基");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());

        object.setName("闪电球");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());

        object.setName("古丽孜哈.买买提");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(1, constraintViolations.size());

        object.setName("古丽孜哈·买买提");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());

        object.setName("古丽孜哈··买买提");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(1, constraintViolations.size());

        object.setName("古丽孜哈·买买提·买买提·买买提");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());

        object.setName("·买买提");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(1, constraintViolations.size());

        object.setName("买买提·");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(1, constraintViolations.size());

        object.setName("艹尼玛");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(0, constraintViolations.size());

        object.setName("orz");
        constraintViolations = validator.validateProperty(object, "name");
        assertEquals(1, constraintViolations.size());
    }
}

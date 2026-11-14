package org.jmaniquet.contrib.springboot.mockitospybean.bug;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.jmaniquet.contrib.springboot.mockitospybean.bug.WithNestedClassTest.WithNestedClassTestConfig;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(controllers = DummyController.class)
@Import(WithNestedClassTestConfig.class)
class WithNestedClassTest {

    @Autowired
    private MockMvcTester mockMvc;

    @MockitoSpyBean
    private DummyService dummyService;

    @Nested
    class FooTest {

        @Test
        void foo() {

            doReturn("foo").when(dummyService).foo();

            mockMvc.get()
                .uri("/dummy")
                .assertThat()
                .hasStatusOk()
                .bodyText().isEqualTo("foo");

            verify(dummyService, times(1)).foo();
        }
    }

    @Nested
    class BarTest {

        @Test
        void bar() {

            doReturn("bar").when(dummyService).foo();

            mockMvc.get()
                .uri("/dummy")
                .assertThat()
                .hasStatusOk()
                .bodyText().isEqualTo("bar");

            verify(dummyService, times(1)).foo();
        }
    }

    @TestConfiguration
    static class WithNestedClassTestConfig {

        @Bean
        DummyService DummyService() {
            System.out.println("Hello test with @Nested");
            return new DummyServiceImpl();
        }
    }
}

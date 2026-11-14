package org.jmaniquet.contrib.springboot.mockitospybean.bug;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(controllers = DummyController.class)
class WithoutNestedClassTest {

    @Autowired
    private MockMvcTester mockMvc;

    @MockitoSpyBean
    private DummyService dummyService;

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

    @TestConfiguration
    static class WithoutNestedClassTestConfig {

        @Bean
        DummyService DummyService() {
            System.out.println("Hello test without @Nested");
            return new DummyServiceImpl();
        }
    }
}

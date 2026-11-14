package org.jmaniquet.contrib.springboot.mockitospybean.bug;

import org.springframework.stereotype.Service;

@Service
class DummyServiceImpl implements DummyService {

    @Override
    public String foo() {
        throw new UnsupportedOperationException("Should be mocked with the spy");
    }

    @Override
    public String bar(String value) {
        return value;
    }

}

package example.service;

import org.springframework.stereotype.Service;

/**
 * @author xushaopeng
 * @date 2018/12/25
 */
@Service
public class ExampleService {

    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }
}

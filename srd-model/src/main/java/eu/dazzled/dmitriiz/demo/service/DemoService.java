package eu.dazzled.dmitriiz.demo.service;

import eu.dazzled.dmitriiz.demo.model.DemoRequest;
import eu.dazzled.dmitriiz.demo.model.DemoResponse;

public interface DemoService {
    DemoResponse perform(DemoRequest request);
}

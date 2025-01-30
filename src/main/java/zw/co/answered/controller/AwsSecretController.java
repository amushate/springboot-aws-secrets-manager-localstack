package zw.co.answered.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.co.answered.service.AwsGateService;

@RestController
@RequestMapping("ap1/v1/aws-secret")
public class AwsSecretController {

    private final AwsGateService awsGateService;

    public AwsSecretController(AwsGateService awsGateService) {
        this.awsGateService = awsGateService;
    }

    @GetMapping
    public String getSecret(@RequestParam("secretName") String secretName) {
        return awsGateService.getSecret(secretName);
    }
}

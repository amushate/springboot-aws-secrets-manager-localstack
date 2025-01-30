package zw.co.answered.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.net.URI;

@Service
public class AwsGateService {

    @Value("${aws-secrets-manager-url}")
    private String awsSecretsManagerUrl;
    private SecretsManagerClient secretsManager;

    @PostConstruct
    public void init() {
        Region region = DefaultAwsRegionProviderChain.builder().build().getRegion();
        secretsManager = SecretsManagerClient.builder().endpointOverride(URI.create(awsSecretsManagerUrl))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(region)
                .build();
    }

    public String getSecret(String secretName) {
        GetSecretValueRequest secretRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
        GetSecretValueResponse secretValue = secretsManager.getSecretValue(secretRequest);
        return secretValue.secretString();
    }
}

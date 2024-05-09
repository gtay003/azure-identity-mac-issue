package azureidentitymacissue

import com.azure.security.keyvault.secrets.SecretClient
import com.azure.security.keyvault.secrets.models.KeyVaultSecret
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(private val secretClient: SecretClient) {
    private val secret: KeyVaultSecret = secretClient.getSecret("test")

    @GetMapping
    suspend fun secretName(): String = secret.name
}
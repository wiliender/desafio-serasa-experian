package com.experian.serasa.rest.api.consumers;

import com.experian.serasa.rest.api.consumers.dto.CepConsumerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CepConsumer {
  private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

  public CepConsumerResponse get(String cep) {

    RestTemplate restTemplate = new RestTemplate();

    String url = VIA_CEP_URL.replace("{cep}", cep);
    try {
      return restTemplate.getForEntity(url, CepConsumerResponse.class).getBody();
    } catch (Exception ex) {
      return null;
    }
  }
}

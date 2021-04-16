package pe.com.ripleyperu.application.api;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.com.ripleyperu.application.api.dto.ResolveRequestDto;
import pe.com.ripleyperu.common.Decrypt;

@Slf4j
@RestController
@RequestMapping(value = "/aes/encrypt")
public class EncryptApplicationController {

//  private static final String ISSUER_SECRET = "5EBE2294ECD0E0F08EAB7690D2A6EE69";
//  private static final String TEXT = "YhjR7tUgQ4NzUKYeTavKjECjVXHZWzSjIuRI1B8XJp98nZwzuu/QicWmhfKUlzUydoqf0uzrzxlYsraO+eSximRI4+U/HAY2h7DD0amo0szsh6mQ3UwzkUa/2udKELJwGFgQYryFMOjnqmzeDmFmbnzS/ztf8Q/19BiC6hIl02E/tEbAEscSN59sX79pFL7EgbGJwor/N5TglPINArmdxDTbcRFYiDoSwHD0aQRBzBmIMH7N1niSq1WBzerrzwOwO7PsAaLK+Q0sriD7s0czJptSwbrkGDrObp2ztYcMZYYjwbxPprTAscw2q1Is3nCIGR/c+1Wn7uwK/px9EVX/rH98gJC5+VBPE06iN8YHLsKRDWhs9fWh/+ynZ+RhGAMBOqmhjl8BQSRbDsE91dFl3brtx4NnNnH13pjH1hnhBRiyJ0CiYz/T9GbxFmijjbJ8/n8A97BbeQX0nW0LZ6VksTOrolwZEuQ5SFvJfKXAYqpbpLiXXPdSKlkDhMFm/CoZduoacwo23H5dSfPNO2m38eQ+7cwj+ZsaQ/hvzL/v9mf7+q1wENjr/FBKlG3OPlT9t2Gjz9rsQhE1A9S87HtTRSvgKj/zFrYuPWbDytBbk29S9f/cB/lomgTs7DzaVvTNGhAHbd5FgMgatlf2qm730x1ncj797V+fY96fohYH65YMov2YGH+c3woR+Jd4SmYkIS+P2nwYHcYZg1b6CaRMqTVSrqd1vdcNTc7fECvnO1sQcQ6udGCrajZ1Dm+NLqNjJenNbqXeI9TuDhMPGsyCUP+nYMHtYMBDkNTzAa8y6MyELlfzLqZjt6Sqo2SP9+DGWhGkmv7jxyNIB6soKDx7DIiVlLdr+exMTSnncIbiVJuW1YhYJ0Z+Yd503/AkL7nPBE4POo/J4CNgOrLoYMBkVZcCwxuQROs5p/iRapEI+crJLYT2yz/fQaX8wmS7OJ7Rm+Tcpp3kYQmUiCS2cFpM666P2n7uPfHlJ4Xe7OpHnwNTpkNM8Z0MEVRzl+yc/g7wiZQLUWxEqhcf153I0gA7glTmserKYe2Fb/QvjpvfmI2QzhbX4DDgS19ep8SJntAZvAq1Ewl7JN5Bpp81Zriixot22CJ4+f7sdIbmRX4AgfRq4OLabItqAYVvsJtCUGW3a53VEvBexrolEuPMRSNNpd0nzLGWPkjqoFG28h/nAicO+PuZv/FbQuxmgWCcwtqq/VXWU+uW79+GL+UvqbxrbFujsbHr7XKzlZX6mg3tOB9oV2esTOFxnNuMNSUlN6pdr/zxmU7gOZTKkf0M5eTeE6TWJeDZvn5mpIvZuR4JylSsj1QMl1mzLVxshvXqJcLGRMJi2APc6S3+kHuZixmGJGa5lIXn4mvDZLWYiixh29/vPRP/OyaDpbxD8qGButqgLNE2qXR5RFOPz5VCaQbZ+KE+Y1K/UaiHjiXVy5aV63Fr9XWxVT2fAUbyFjiHk06VApFh8usSRjb7kpTnI4+alEImNTi0EDP6ehMmAc9NbKK8K+OlVZQ1TDZ5dyK5DuIzhX9QbsiQ4qHIIWk1IF3fWUTJHnydCcsxa3o6aa1Fg+Z21qgdX4LfeCqsQc3AxOaeyS2E9ss/30Gl/MJkuzie0VTEvMgMVBTelmI0IUKgx1ZDVnSRL2pFNG9K8iD4AJ+JUMdOHIbXGohlYtaVmgg1+UbtQFaGlIKNWu7PpNCnf3SxQBCbb5fjBtNesFC2ruF0eYCA8fX341m9cgkW2HC9cA==";

  @Autowired
  Decrypt decrypt;

  @GetMapping("/")
  public String hello() {
    return "Hola mama";
  }

  @PostMapping(value = "/resolve")
  public ResponseEntity<Object> getValue(@RequestHeader(value = "issuer-secret") String issuerSecret,
      @RequestBody List<ResolveRequestDto> requestDto) throws InvalidKeyException, NoSuchAlgorithmException,
      NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    log.info("into getvalue():"+issuerSecret);

    try {
      List<String> response = new ArrayList<String>();
      log.info("paso1");
      for (ResolveRequestDto aux : requestDto) {
        log.info("getRawValue:" + aux.getRawValue());
        String ves =decrypt.decrypt(aux.getRawValue(), issuerSecret);
        response.add(ves);
      }
      log.info("paso2");
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      log.info("exception: "+e);
      return new ResponseEntity<Object>("Super error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}

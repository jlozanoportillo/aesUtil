package pe.com.ripleyperu.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolveRequestDto {

  @JsonProperty(value = "rawValue")
  private String rawValue;

}

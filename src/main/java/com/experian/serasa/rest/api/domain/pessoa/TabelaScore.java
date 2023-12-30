package com.experian.serasa.rest.api.domain.pessoa;

public enum TabelaScore {
  INSUFICIENTE("Insuficiente"),
  INACEITAVEL("Inaceitável"),
  ACEITAVEL("Aceitável"),
  RECOMENDAVEL("Recomendável");

  private String tabelaScore;

  private TabelaScore(String tabelaScore) {
    this.tabelaScore = tabelaScore;
  }

  @Override
  public String toString() {
    return tabelaScore;
  }

  public static String getScoreStatus(int score) {

    if (score > 700) {
      return RECOMENDAVEL.toString();
    } else if (score > 500) {
      return ACEITAVEL.toString();
    } else if (score > 200) {
      return INACEITAVEL.toString();
    } else {
      return INSUFICIENTE.toString();
    }
  }
}

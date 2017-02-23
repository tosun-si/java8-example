package com.tosunsi.example.designpattern.java8.chainofresp;

import java.util.Optional;

/**
 * Created by Mazlum on 19/12/2016.
 */
public class ChainOfResp {

  public enum Team {
    REAL, BARCA, MU, PSG;
  }

  private Optional<String> getRealPlayerThatScoredGreasterThan20(final Team team ){
      return Optional.of("");
  }

}

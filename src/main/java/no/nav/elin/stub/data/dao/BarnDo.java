package no.nav.elin.stub.data.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import no.nav.elin.stub.data.dto.Barn;

@Getter
@Setter
@Entity
@Table(name = "barn", schema = "public")
public class BarnDo extends Barn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_rolle")
  @Setter(AccessLevel.NONE)
  private RolleDo rolleDo;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_sak")
  private SakDo sakDo;

  public void setRolle(RolleDo rolle) {
    this.rolleDo = rolle;
  }

}

package no.nav.elin.stub.data.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import no.nav.elin.stub.data.dto.Transaksjon;

@Getter
@Setter
@Entity
@Table(name = "transaksjon", schema = "public")
public class TransaksjonDo extends Transaksjon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_sak")
  @Setter(AccessLevel.NONE)
  private SakDo sakDo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_rolle_betaler")
  @Setter(AccessLevel.NONE)
  private RolleDo rolleDoTransaksjonBetaler;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_rolle_mottaker")
  @Setter(AccessLevel.NONE)
  private RolleDo rolleDoTransaksjonMottaker;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_rolle_barn")
  @Setter(AccessLevel.NONE)
  private RolleDo rolleDoBarn;

  public void setSakDo(SakDo sak) {
    this.sakDo = sak;
  }

  public void setRolleDoTransaksjonBetaler(RolleDo rolleTransaksjonBetaler) {
    this.rolleDoTransaksjonBetaler = rolleTransaksjonBetaler;
  }

  public void setRolleDoTransaksjonMottaker(RolleDo rolleTransaksjonMottaker) {
    this.rolleDoTransaksjonMottaker = rolleTransaksjonMottaker;
  }

  public void setRolleDoBarn(RolleDo rolleBarn) {
    this.rolleDoBarn = rolleBarn;
  }

}

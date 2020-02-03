package no.nav.elin.stub.data.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import no.nav.elin.stub.data.dto.Person;

@Getter
@Setter
@Entity
@Table(name = "person", schema = "public")
public class PersonDo extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_rolle")
  @Setter(AccessLevel.NONE)
  private RolleDo rolleDoPerson;

  public void setRollePerson(RolleDo rollePerson) {
    this.rolleDoPerson = rollePerson;
  }

}

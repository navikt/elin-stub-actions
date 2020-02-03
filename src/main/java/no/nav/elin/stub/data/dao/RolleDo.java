package no.nav.elin.stub.data.dao;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rolle", schema = "public")
public class RolleDo {

  public static enum rolleType {
    BA, BM, BP
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_fnr")
  private FnrDo fnrDo;

  private String rolleType;

  @OneToMany(mappedBy = "rolleDoBm", cascade = CascadeType.ALL)
  private Set<SakDo> sakDosBm = new HashSet<>();

  @OneToMany(mappedBy = "rolleDoBp", cascade = CascadeType.ALL)
  private Set<SakDo> sakDosBp = new HashSet<>();

  @OneToMany(mappedBy = "rolleDo", cascade = CascadeType.ALL)
  private Set<BarnDo> barnDos = new HashSet<>();


}

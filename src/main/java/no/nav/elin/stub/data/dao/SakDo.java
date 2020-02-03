package no.nav.elin.stub.data.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sak", schema = "public",
    indexes = {@Index(name = "saksnr_idx", columnList = "saksnr")})
public class SakDo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ApiModelProperty(required = true, example = "1900000")
  @Column(unique = true)
  private int saksnr;

  @ApiModelProperty(required = true, example = "5000")
  private BigDecimal dbmGjeldFastsGebyr;

  @ApiModelProperty(required = true, example = "0")
  private BigDecimal dbmGjeldRest;

  @ApiModelProperty(required = true, example = "8000")
  private BigDecimal dbpGjeldFastGebyr;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_rolle_bp")
  @Setter(AccessLevel.NONE)
  private RolleDo rolleDoBp;

  @ManyToOne(cascade = CascadeType.ALL)
  @Setter(AccessLevel.NONE)
  @JoinColumn(name = "fk_rolle_bm")
  private RolleDo rolleDoBm;

  @OneToMany(mappedBy = "sakDo", cascade = CascadeType.ALL)
  private Set<BarnDo> barnDos = new HashSet<>();

  @OneToMany(mappedBy = "sakDo", cascade = CascadeType.ALL)
  private Set<TransaksjonDo> transaksjonDos = new HashSet<>();

  public void setRolleDoBp(RolleDo rolleBp) {
    this.rolleDoBp = rolleBp;
  }

  public void setRolleDoBm(RolleDo rolleBm) {
    this.rolleDoBm = rolleBm;
  }
}

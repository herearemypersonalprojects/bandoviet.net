package net.bandoviet.mail;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Mail object.
 * 
 * @author quocanh
 *
 */
public class Mail {
  //private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);
  
  /**
   * Vietnamese accents.
   */
  public static final String DOCUMENT_CHARSET = "UTF-8";

  private Long id;
  private String from;
  private String subject;
  private String text;
  private String[] to;
  private String[] cc; // copie
  private String[] bbc; // copie cachée
  private List<Attachment> listeAttachments;

  /**
   * @param id
   *          the id to set.
   */
  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String[] getTo() {
    return to;
  }

  public void setTo(String... to) {
    this.to = to;
  }

  /**
   * destinations.
   * 
   * @param to
   *          receivers' mail
   */
  public void setTo(List<String> to) {
    if ((to != null) && (to.size() > 0)) {
      String[] destinataires = new String[to.size()];
      for (int i = 0; i < to.size(); i++) {
        destinataires[i] = to.get(i);
      }
      setTo(destinataires);
    }
  }

  public void setCc(String... cc) {
    this.cc = cc;
  }

  public String[] getCc() {
    return cc;
  }

  public void setBbc(String... bbc) {
    this.bbc = bbc;
  }

  public String[] getBbc() {
    return bbc;
  }

  /**
   * Ajoute un attachement au mail de type fichier.
   *
   * @param name
   *          file's name
   * @param byteArray
   *          content.
   */
  public void addAttachment(String name, byte[] byteArray) {
    addAttachment(new Attachment(name, null, new ByteArrayResource(byteArray)));
  }

  /**
   * Ajoute un attachement au mail.
   *
   * @param attachment
   *          file to be attached.
   */
  private void addAttachment(Attachment attachment) {
    if (listeAttachments == null) {
      listeAttachments = new ArrayList<Attachment>();
    }
    listeAttachments.add(attachment);
  }

  public List<Attachment> getListeAttachments() {
    return listeAttachments;
  }


  /**
   * Class utilisée pour passé des attachement au helper.
   *
   */
  public static class Attachment {
    private final String nom;
    private final String fichier;
    private final InputStreamSource inputStreamSource;

    Attachment(String nom, String fichier, InputStreamSource inputStreamSource) {
      super();
      this.nom = nom;
      this.fichier = fichier;
      this.inputStreamSource = inputStreamSource;
    }

    public String getNom() {
      return nom;
    }

    public String getFichier() {
      return fichier;
    }

    public InputStreamSource getInputStreamSource() {
      return inputStreamSource;
    }
  }
}

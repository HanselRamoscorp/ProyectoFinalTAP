package sample.Modelos;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.IOException;

@WrapToTest
public class Ticket {
    public static final String imagen = "src/Pictures/Fondos/linpay.png";

    public void createBUSTK(String dest ,Object bTK) throws IOException {
     Busticket  bustk= Busticket.class.cast(bTK);
     String compañia = bustk.id_bus.id_linebus.id_company.name;
     int NoBoleto = bustk.id_busticket;
     String NomCliente = bustk.clientname;
     String payDate = bustk.pay_date;
     int NoAutoB = bustk.id_bus.id_bus;
     String Salida = bustk.id_bus.outdate;
     String origen = bustk.id_bus.id_origin.city;
     String destino = bustk.id_bus.id_destiny.city;
     String TipoViaje = bustk.id_bus.id_linebus.trip;
     int costo = bustk.id_bus.amount;

        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        //Document document = new Document(pdf, pageSize);
        Document document = new Document(pdf);
        document.setMargins(50, 180, 50, 180);

        PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

        Text title = new Text("Sistema de pagos de servicios").setFont(font1).setFontSize(15);
        Text subtitle = new Text("\n Av. García Cubas 1200 \n Esquina Ignacio Borunda \n Celaya, Gto. México \nCENTRO DE ATENCION").setFont(font2).setFontSize(10);

        Paragraph p1 = new Paragraph().add(title).add("\n").add(subtitle);
        p1.setTextAlignment(TextAlignment.CENTER);

        //DottedLine dottedLine = new DottedLine(1); Linea punteada
        DashedLine dashedLine = new DashedLine(1);

        Text text1 = new Text("Ticket DE PAGO").setFont(font2).setFontSize(10).setBold();
        Paragraph p2 = new Paragraph().add(text1).add("\n\n");
        p2.setTextAlignment(TextAlignment.CENTER);

        Text text2 = new Text("Empresa : "+ compañia).setFont(font2).setFontSize(10);
        Text text3 = new Text("Numero de Boleto: "+ NoBoleto).setFont(font2).setFontSize(10);
        Text text4 = new Text("Pasajero: "+ NomCliente).setFont(font2).setFontSize(10);
        Text text5 = new Text("Fecha del Pago: "+payDate).setFont(font2).setFontSize(10);
        Text text7 = new Text("Numero de Autobus: "+NoAutoB).setFont(font2).setFontSize(10);
        Text text8 = new Text("Tiempo de Salida: "+ Salida).setFont(font2).setFontSize(10);
        Text text9 = new Text("Origen: "+origen).setFont(font2).setFontSize(10);
        Text text10 = new Text("Destino: "+destino).setFont(font2).setFontSize(10);
        Text text11 = new Text("Tipo de Viaje: "+TipoViaje).setFont(font2).setFontSize(10);
        Text text12 = new Text("Costo: "+costo).setFont(font2).setFontSize(10);
        Paragraph p3 = new Paragraph().add(text2).add("\n").add(text3).add("\n").add(text4).add("\n").add(text5).add("\n")
                .add(text7).add("\n").add(text8).add("\n").add(text9).add("\n").add(text10).add("\n").add(text11).add("\n")
                .add(text12).add("\n").add(new LineSeparator(dashedLine));
        p3.setTextAlignment(TextAlignment.LEFT);



        /*Text text12 = new Text("LLAMADAS REALIZADAS: "+calls.getTelephone_number()).setFont(font2).setFontSize(10);
        Paragraph p5 = new Paragraph().add(text12).add("\n");
        p5.setTextAlignment(TextAlignment.LEFT);*/

        Image telmex = new Image(ImageDataFactory.create(imagen));
        //telmex.setWidth(200).setHeight(100).setTextAlignment(TextAlignment.CENTER);

        //Add paragraph to the document
        document.add(telmex).add(new Paragraph("\n")).add(p1).add(new LineSeparator(dashedLine)).add(p2).add(p3);

        //Close document
        document.close();
    }

    public void createRecharge(String dest, Recharge bTK) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(50, 180, 50, 180);
        PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

        Text title = new Text("Sistema de pagos de servicios").setFont(font1).setFontSize(15);
        Text subtitle = new Text("\n Av. García Cubas 1200 \n Esquina Ignacio Borunda \n Celaya, Gto. México \nCENTRO DE ATENCION").setFont(font2).setFontSize(10);

        Paragraph p1 = new Paragraph().add(title).add("\n").add(subtitle);
        p1.setTextAlignment(TextAlignment.CENTER);

        //DottedLine dottedLine = new DottedLine(1); Linea punteada
        DashedLine dashedLine = new DashedLine(1);

        Text text1 = new Text("Ticket DE PAGO").setFont(font2).setFontSize(10).setBold();
        Paragraph p2 = new Paragraph().add(text1).add("\n\n");
        p2.setTextAlignment(TextAlignment.CENTER);

        Text text2 = new Text("Compañia : "+ bTK.getId_phoneplan().id_company.getName()).setFont(font2).setFontSize(10);
        Text text3 = new Text("Numero de telefono: "+ bTK.getPhonenumber()).setFont(font2).setFontSize(10);
        Text text4 = new Text("Recarga de "+ bTK.getId_phoneplan().getQuantity()).setFont(font2).setFontSize(10);
        Paragraph p3 = new Paragraph().add(text2).add("\n").add(text3).add("\n").add(text4).add(new LineSeparator(dashedLine));
        p3.setTextAlignment(TextAlignment.LEFT);

        Image telmex = new Image(ImageDataFactory.create(imagen));
        document.add(telmex).add(new Paragraph("\n")).add(p1).add(new LineSeparator(dashedLine)).add(p2).add(p3);

        document.close();
    }

    /*public void createHomeService(String dest, HomeService bTK) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(50, 180, 50, 180);
        PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

        Text title = new Text("Sistema de pagos de servicios").setFont(font1).setFontSize(15);
        Text subtitle = new Text("\n Av. García Cubas 1200 \n Esquina Ignacio Borunda \n Celaya, Gto. México \nCENTRO DE ATENCION").setFont(font2).setFontSize(10);

        Paragraph p1 = new Paragraph().add(title).add("\n").add(subtitle);
        p1.setTextAlignment(TextAlignment.CENTER);

        //DottedLine dottedLine = new DottedLine(1); Linea punteada
        DashedLine dashedLine = new DashedLine(1);

        Text text1 = new Text("Ticket DE PAGO").setFont(font2).setFontSize(10).setBold();
        Paragraph p2 = new Paragraph().add(text1).add("\n\n");
        p2.setTextAlignment(TextAlignment.CENTER);

        Text text2 = new Text("Compañia : "+ bTK.getId_phoneplan().id_company.getName()).setFont(font2).setFontSize(10);
        Text text3 = new Text("Numero de telefono: "+ bTK.getPhonenumber()).setFont(font2).setFontSize(10);
        Text text4 = new Text("Recarga de "+ bTK.getId_phoneplan().getQuantity()).setFont(font2).setFontSize(10);
        Paragraph p3 = new Paragraph().add(text2).add("\n").add(text3).add("\n").add(text4).add(new LineSeparator(dashedLine));
        p3.setTextAlignment(TextAlignment.LEFT);

        Image telmex = new Image(ImageDataFactory.create(imagen));
        document.add(telmex).add(new Paragraph("\n")).add(p1).add(new LineSeparator(dashedLine)).add(p2).add(p3);

        document.close();
    }*/
}

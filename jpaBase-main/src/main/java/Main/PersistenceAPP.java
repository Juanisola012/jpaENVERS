package Main;
import entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class PersistenceAPP {
        public static void main(String[] args){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceApp");
            EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Factura factura1 = new Factura();
            factura1.setNumero(12);
            factura1.setFecha("10/08/2020");

            Domicilio dom = new Domicilio("lujan", 122);
            Cliente cliente = new Cliente("Raul","Rodriguez",212121);
            dom.setCliente(cliente);
            factura1.setCliente(cliente);

            Categoria perecederos = new Categoria("Percederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1 = new Articulo(200,"Yogur Ser",20);
            Articulo art2 = new Articulo(300,"Detergente",80);

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = new DetalleFactura();

            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(40);

            art1.getDetalle().add(det1);
            factura1.getDetalles().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = new DetalleFactura();

            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(80);

            art2.getDetalle().add(det2);
            factura1.getDetalles().add(det2);
            det2.setFactura(factura1);

            factura1.setTotal(120);

            /*Pruebas realizadas para la base de datos */

            /*Factura factura1 = em.find(Factura.class, 1L);
            factura1.setNumero(85);*/

            em.persist(factura1);

            em.flush();

            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
            em.close();
            emf.close();
        }
}

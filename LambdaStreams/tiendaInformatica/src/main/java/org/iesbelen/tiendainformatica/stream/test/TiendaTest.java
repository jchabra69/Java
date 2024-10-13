package org.iesbelen.tiendainformatica.stream.test;

import org.iesbelen.tiendainformatica.dao.FabricanteDAOImpl;
import org.iesbelen.tiendainformatica.dao.ProductoDAOImpl;
import org.iesbelen.tiendainformatica.entity.Fabricante;
import org.iesbelen.tiendainformatica.entity.Producto;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TiendaTest {

    private final FabricanteDAOImpl fabricantesDAOImpl;
    private final ProductoDAOImpl productosDAOImpl;


    public TiendaTest() {
        Fabricante fabricante;
        fabricantesDAOImpl = new FabricanteDAOImpl();
        productosDAOImpl = new ProductoDAOImpl();
        try {
            fabricantesDAOImpl.beginTransaction();
            productosDAOImpl.beginTransaction();

            // Creación de fabricantes y productos
            // Asus
            fabricante = new Fabricante("Asus");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "Monitor 27 LED Full HD", 199.25));
            productosDAOImpl.create(new Producto(fabricante, "Monitor 24 LED Full HD", 119.99));

            // Lenovo
            fabricante = new Fabricante("Lenovo");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "Portátil IdeaPad 320", 359.65));
            productosDAOImpl.create(new Producto(fabricante, "Portátil Yoga 520", 452.79));

            // Hewlett-Packard
            fabricante = new Fabricante("Hewlett-Packard");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "Impresora HP Deskjet 3720", 59.99));
            productosDAOImpl.create(new Producto(fabricante, "Impresora HP Laserjet Pro M26nw", 111.86));

            // Samsung
            fabricante = new Fabricante("Samsung");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "Disco SSD 1 TB", 72.99));

            // Seagate
            fabricante = new Fabricante("Seagate");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "Disco duro SATA3 1TB", 38.49));

            // Crucial
            fabricante = new Fabricante("Crucial");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "GeForce GTX 1080 Xtreme", 611.55));
            productosDAOImpl.create(new Producto(fabricante, "Memoria RAM DDR4 8GB", 24.19));

            // Gigabyte
            fabricante = new Fabricante("Gigabyte");
            fabricantesDAOImpl.create(fabricante);
            productosDAOImpl.create(new Producto(fabricante, "GeForce GTX 1050Ti", 319.19));

            // Huawei sin productos a insertar
            fabricante = new Fabricante("Huawei");
            fabricantesDAOImpl.create(fabricante);

            // Xiaomi sin productos a insertar
            fabricante = new Fabricante("Xiaomi");
            fabricantesDAOImpl.create(fabricante);
        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    @Test
    void testSkeletonFrabricante() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();


            //TODO STREAMS


        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }


    @Test
    void testSkeletonProducto() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    @Test
    void testAllFabricante() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();
            assertEquals(9, listFab.size());

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    @Test
    void testAllProducto() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();
            assertEquals(11, listProd.size());
        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 1. Lista los nombres y los precios de todos los productos de la tabla producto
     */
    @Test
    void test1() {
        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()
                    .map(prod -> "Nombre: " + prod.getNombre() + ", Precio: " + prod.getPrecio()) // Modifico la salida
                    .forEach(System.out::println); // Mostramos el resultado

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }


    /**
     * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares.
     */
    @Test
    void test2() {

        try {
            productosDAOImpl.beginTransaction();
            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()
                    .map(producto -> producto.toString() + " Precio en EURO: " + producto.getPrecio()
                            //Los 100 son para redondear a 2 décimas
                            + "\nPrecio en DÓLARES: " + Math.round(producto.getPrecio() * 1.0965 * 100.0) / 100.0)
                    .forEach(System.out::println);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
     */
    @Test
    void test3() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            listProd.stream()

                    .map(producto -> "Nombre: " + producto.getNombre().toUpperCase() + ", Precio: " + producto.getPrecio())
                    .forEach(System.out::println);

            //TODO STREAMS

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
     */
    @Test
    void test4() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS
            listFab.stream()

                    .map(fabricante -> "Nombre: " + fabricante.getNombre() + " DosPrimerosCaracteres: " + fabricante.getNombre().substring(0, 2).toUpperCase())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 5. Lista el código de los fabricantes que tienen productos.
     */
	@Test
	void test5() {
		try {
			fabricantesDAOImpl.beginTransaction();

			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			listFab.stream()
					.filter(fabricante -> !fabricante.getProductos().isEmpty())
					.map(Fabricante::getIdFabricante)
					.forEach(codigo -> System.out.println("Código del fabricante con PRODUCTOS: " + codigo));

		} catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
			throw e;
		}
	}

    /**
     * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
     */
    @Test
    void test6() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            listFab.stream()
                    .sorted(Comparator.comparing(Fabricante::getNombre).reversed())
                    .map(fabricante -> "Nombre: " + fabricante.getNombre())
                    .forEach(System.out::println);

            /*
            * Es más eficiente ordenar después de map, tal como
            * .map
            * .sorted
            * */

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 7. Lista los nombres de los productosDAOImpl ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
     */
    @Test
    void test7() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            listProd.stream()
                    .sorted(Comparator.comparing(Producto::getNombre)
                            //Debo usar esto y volver a declarar otro Comparator para no afectar al orden del otro
                            .thenComparing(Comparator.comparing(Producto::getPrecio).reversed()))

                    .map(producto -> "Nombre producto: " + producto.getNombre() + " Precio: " + producto.getPrecio())

                    .forEach(System.out::println);

            //TODO STREAMS

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 8. Devuelve una lista con los 5 primeros fabricantes.
     */
    @Test
    void test8() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            listFab.stream()
                    .limit(5)
                    .forEach(System.out::println);

            //TODO STREAMS

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
     */
    @Test
    void test9() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            listFab.stream()
                    .skip(3)
                    .limit(2)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 10. Lista el nombre y el precio del producto más barato
     */
    @Test
    void test10() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

                    listProd.stream()
                    .min(Comparator.comparing(Producto::getPrecio)) // Encuentra el producto más barato
                            .ifPresent(producto -> System.out.println("Nombre del producto más barato: " + producto.getNombre() + ", Precio: " + producto.getPrecio()));





        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 11. Lista el nombre y el precio del producto más caro
     */
    @Test
    void test11() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()

                    .max(Comparator.comparing(Producto::getPrecio))
                    //Si el lambda anterior es optional, debo usar ifPresent
                    .ifPresent(producto -> System.out.println("Nombre del producto más caro: " + producto.getNombre() + ", Precio: " + producto.getPrecio()));

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
     */
    @Test
    void test12() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();


            //TODO STREAMS
            listProd.stream()

                    .filter(producto -> producto.getFabricante().getIdFabricante() == 2)
                    .map(producto -> "Nombre: " +producto.getNombre() + " Código: " +producto.getFabricante().getIdFabricante())
                    .forEach(System.out::println);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
     */
    @Test
    void test13() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            listProd.stream()
                    .filter(producto -> producto.getPrecio() <= 120)
                    .map(producto -> "Nombre: " +producto.getNombre() + ", Precio: " + producto.getPrecio())
                    //Me puedo ahorrar el map modificado el string en el for each
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 14. Lista los productos que tienen un precio mayor o igual a 400€.
     */
    @Test
    void test14() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()
                    .filter(producto -> producto.getPrecio() >= 400)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 15. Lista todos los productos que tengan un precio entre 80€ y 300€.
     */
    @Test
    void test15() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()

                    .filter(producto -> producto.getPrecio() >= 80 && producto.getPrecio() <= 300)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
     */
    @Test
    void test16() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()

                    .filter(producto -> producto.getPrecio() >= 200 && producto.getFabricante().getIdFabricante() == 6)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
     */
    @Test
    void test17() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            Set<Integer> fabricanteIds = new HashSet<>(Arrays.asList(1, 3, 5));


            Set<Producto> productosFiltrados = listProd.stream()
                    .filter(producto -> fabricanteIds.contains(producto.getFabricante().getIdFabricante()))
                    .collect(Collectors.toSet());


            productosFiltrados.forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // o mostrar un mensaje de error
        }
    }

    /**
     * 18. Lista el nombre y el precio de los productos en céntimos.
     */
    @Test
    void test18() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            listProd.stream()

                    .map(producto -> "Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() * 100 + " ´centimos")
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }


    /**
     * 19. Lista los nombres de los fabricante cuyo nombre empiece por la letra S
     */
    @Test
    void test19() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            listFab.stream()

                    .filter(fabricante -> fabricante.getNombre().startsWith("S"))
                    .map(fabricante -> "Nombre: " +fabricante.getNombre())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }

    }

    /**
     * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
     */
    @Test
    void test20() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            List<Producto> productosPortatil  = listProd.stream()


                    .filter(producto -> producto.getNombre().toLowerCase().contains("Portátil".toLowerCase()))
                    .collect(Collectors.toList());
            productosPortatil.forEach(System.out::println);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
     */
    @Test
    void test21() {
        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            // Filtro productos que contengan "monitor" en el nombre y cuyo precio sea menor a 215
            List<String> productosFiltrados = listProd.stream()

                    .filter(producto -> producto.getNombre().toLowerCase().contains("monitor") && producto.getPrecio() < 215)

                    .map(producto -> "Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio())

                    .collect(Collectors.toList());


            productosFiltrados.forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€.
     * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
     */
    @Test
    void test22() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()

                    .filter(producto -> producto.getPrecio() >= 180)
                    .sorted(Comparator.comparing(Producto::getPrecio).reversed()
                            .thenComparing(Comparator.comparing(Producto::getNombre)))
                    .map(producto -> "Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos.
     * Ordene el resultado por el nombre del fabricante, por orden alfabético.
     */
    @Test
    void test23() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            List<String> productosInfo = listProd.stream()
                    .sorted(Comparator.comparing(producto -> producto.getFabricante().getNombre()))
                    .map(producto -> "Nombre del producto: " + producto.getNombre() +
                            ", Precio: " + producto.getPrecio() +
                            ", Fabricante: " + producto.getFabricante().getNombre())
                    .collect(Collectors.toList());


            productosInfo.forEach(System.out::println);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
     */
    @Test
    void test24() {


        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            listProd.stream()
                    .max(Comparator.comparing(Producto::getPrecio))
                    .map(producto -> "Nombre del producto: " + producto.getNombre() +
                            ", Precio: " + producto.getPrecio() +
                            ", Fabricante: " + producto.getFabricante().getNombre())
                    .ifPresent(System.out::println);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
     */
    @Test
    void test25() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            List<Producto> productosFiltrados = listProd.stream()
                    .filter(producto -> producto.getFabricante().getNombre().equalsIgnoreCase("Crucial") && producto.getPrecio() > 200)
                    .collect(Collectors.toList());

            productosFiltrados.forEach(System.out::println);

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
     */
    @Test
    void test26() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            listProd.stream()

                    .filter(producto -> producto.getFabricante().getNombre().equals("Asus") || producto.getFabricante().getNombre().equals("Hewlett-Packard") ||
                                    producto.getFabricante().getNombre().equals("Seagate"))
                    .forEach(System.out::println);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€.
     * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
     * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
     * La salida debe quedar tabulada como sigue:
     * <p>
     * Producto                Precio             Fabricante
     * -----------------------------------------------------
     * GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
     * Portátil Yoga 520      |452.79            |Lenovo
     * Portátil Ideapd 320    |359.64000000000004|Lenovo
     * Monitor 27 LED Full HD |199.25190000000003|Asus
     */
    @Test
    void test27() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos.
     * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados.
     * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
     * La salida debe queda como sigue:
     * Fabricante: Asus
     * <p>
     * Productos:
     * Monitor 27 LED Full HD
     * Monitor 24 LED Full HD
     * <p>
     * Fabricante: Lenovo
     * <p>
     * Productos:
     * Portátil Ideapd 320
     * Portátil Yoga 520
     * <p>
     * Fabricante: Hewlett-Packard
     * <p>
     * Productos:
     * Impresora HP Deskjet 3720
     * Impresora HP Laserjet Pro M26nw
     * <p>
     * Fabricante: Samsung
     * <p>
     * Productos:
     * Disco SSD 1 TB
     * <p>
     * Fabricante: Seagate
     * <p>
     * Productos:
     * Disco duro SATA3 1TB
     * <p>
     * Fabricante: Crucial
     * <p>
     * Productos:
     * GeForce GTX 1080 Xtreme
     * Memoria RAM DDR4 8GB
     * <p>
     * Fabricante: Gigabyte
     * <p>
     * Productos:
     * GeForce GTX 1050Ti
     * <p>
     * Fabricante: Huawei
     * <p>
     * Productos:
     * <p>
     * <p>
     * Fabricante: Xiaomi
     * <p>
     * Productos:
     */
    @Test
    void test28() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();


            //TODO STREAMS

            //Los productos están asociados a fabricantes gracias a la columna idfabricante
            listFab.stream()

                  //Y hay un metodo que devuelve un set de productos

                    //Así que utilizo flatmap para hacer un stream de producto
                    .flatMap(fabricante -> fabricante.getProductos().stream()

                            //Y necesito map para acceder a ambos y hacer el string
                            .map(producto -> "Fabricante: " +fabricante.getNombre() + "\n Producto: " +producto.getNombre())

                            //RECUERDA QUE EL .MAP DEBE ESTAR DENTRO DEL FLATMAP

                    )

                    //Ahora recorro todo ese stream como de costumbre
                    .forEach(System.out::println);


        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
     */
    @Test
    void test29() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS
            listFab.stream()

                    .filter(fabricante -> fabricante.getProductos().isEmpty())
                    .forEach(fabricante -> System.out.println("Fabricante sin productos: " + fabricante.getNombre()));


        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
     */
    @Test
    void test30() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            long cuenta = listProd.stream().count();

            System.out.println("Hay un total de " +cuenta + " productos");




        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }


    /**
     * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
     */
    @Test
    void test31() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            long cantidadFabricantesConProductos = listProd.stream()

                    //long cuenta = listProd.stream().count();
                    //Filtra por los que no están vacíos
                    .filter(producto -> producto.getFabricante() != null && !producto.getFabricante().getProductos().isEmpty())

                    .distinct() // Asegura que solo contemos cada fabricante una vez
                    // Contamos los fabricantes únicos
                    .count();

            System.out.println("Nº de fabricantes con productos: " + cantidadFabricantesConProductos);



        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }

    }

    /**
     * 32. Calcula la media del precio de todos los productos
     */
    @Test
    void test32() {


        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            OptionalDouble media = listProd.stream()

                    .mapToDouble(Producto::getPrecio)
                    .average();

            System.out.println("La media de los precios es: " +Math.round(media.getAsDouble() *100.0) / 100.0);


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
     */
    @Test
    void test33() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            listProd.stream()

                    .min(Comparator.comparing(Producto::getPrecio))
                    .ifPresent(producto -> System.out.println("El producto más barato es: " +producto.toString()));


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 34. Calcula la suma de los precios de todos los productos.
     */
    @Test
    void test34() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS
            double sumaPreciosProductos = listProd.stream()

                    .mapToDouble(Producto::getPrecio)
                    .sum();

            System.out.println("La suma de los precios de todos los productos equivale a: " +sumaPreciosProductos + "€");

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 35. Calcula el número de productos que tiene el fabricante Asus.
     */
    @Test
    void test35() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            long cuentaProductosAsus = listProd.stream()

                    //Filtro los productos que son de Asus
                    .filter(producto -> producto.getFabricante().getNombre().equalsIgnoreCase("Asus"))
                    //Y ahora debería contar sus productos

                    .count();

            System.out.println("El fabricante Asus tiene: " + cuentaProductosAsus + " productos");

        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 36. Calcula la media del precio de todos los productos del fabricante Asus.
     */
    @Test
    void test36() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();

            //TODO STREAMS

            OptionalDouble media = listProd.stream()

                    .filter(producto -> producto.getFabricante().getNombre().equalsIgnoreCase("Asus"))
                    .mapToDouble(Producto::getPrecio)
                    .average();

            System.out.println("La media del precio de todos los productos del fabricante Asus es: " +media.orElse(0));


        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }


    /**
     * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial.
     * Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
     */
    @Test
    void test37() {

        try {
            productosDAOImpl.beginTransaction();

            List<Producto> listProd = productosDAOImpl.findAll();
            double precioMinimo, precioMaximo, cuenta, media, suma;
            //TODO STREAMS

            /*
            Double[] resultados = listProd.stream()

                    //APRENDER A USAR REDUCER
                    .filter(producto -> producto.getFabricante().getNombre().equalsIgnoreCase("Crucial"))

                    //Reduce devuelve un objeto T, .reduce(a, b) -> c
                    //Pero el profe pide un Double[] como acumulador para devolver un conjunto de resultados


                    .reduce(new Double[] {Double.MAX_VALUE, Double.MIN_VALUE, 0.0, 0.0},

                            (acumulador, productoActual) -> {

                                acumulador[0] = Math.min(acumulador[0], productoActual.getPrecio()); //En esa posición, guarda el mínimo
                                    Math.max(acumulador[1], productoActual.getPrecio()); //En la 1, guarda el máximo

                                    acumulador[2] + productoActual.getPrecio(); //Suma el anterior con el nuevo
                                    acumulador[3] + 1; //Cuenta este




                            })  ;



             precioMinimo = resultados[0];
             precioMaximo = resultados[1];
             suma = resultados[2];
             cuenta = resultados[3];
             media = suma / cuenta;

            System.out.printf("El precio mínimo es: %.2f€%n", precioMinimo);
            System.out.printf("El precio máximo es: %.2f€%n", precioMaximo);
            System.out.printf("La suma de precios es: %.2f€%n", suma);
            System.out.printf("El conteo de productos es: %.0f%n", cuenta);
            System.out.printf("La media de precios es: %.2f€%n", media);

             */





        } catch (RuntimeException e) {
            productosDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 38. Muestra el número total de productos que tiene cada uno de los fabricantes.
     * El listado también debe incluir los fabricantes que no tienen ningún producto.
     * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene.
     * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
     * La salida debe queda como sigue:
     * <p>
     * Fabricante     #Productos
     * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
     * Asus              2
     * Lenovo              2
     * Hewlett-Packard              2
     * Samsung              1
     * Seagate              1
     * Crucial              2
     * Gigabyte              1
     * Huawei              0
     * Xiaomi              0
     */
    @Test
    void test38() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            listFab.stream()

                .forEach(fab -> {long numProductos = fab.getProductos().size(); // Cuenta los productos del fabricante


                System.out.printf("%-20s %d%n", fab.getNombre(), numProductos);
            });


        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes.
     * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
     * Deben aparecer los fabricantes que no tienen productosDAOImpl.
     */
    @Test
    void test39() {
        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            listFab.forEach(fab -> {


                Double[] resultados = fab.getProductos().stream()

                        .map(Producto::getPrecio)

                        .reduce(new Double[]{Double.MAX_VALUE, Double.MIN_VALUE, 0.0, 0.0},

                                (acumulador, precioActual) -> {
                                    // Actualiza el acumulador
                                    acumulador[0] = Math.min(acumulador[0], precioActual);
                                    acumulador[1] = Math.max(acumulador[1], precioActual);
                                    acumulador[2] += precioActual; //Suma el anterior con el actual
                                    acumulador[3] += 1; //Cuenta uno mñas

                                    return acumulador;
                                },
                                (acc1, acc2) -> {
                                    // Combinación de resultados si es necesario
                                    acc1[0] = Math.min(acc1[0], acc2[0]);
                                    acc1[1] = Math.max(acc1[1], acc2[1]);
                                    acc1[2] += acc2[2];
                                    acc1[3] += acc2[3];
                                    return acc1;
                                });

                // Mostrar el nombre del fabricante y los resultados
                double precioMinimo = resultados[0];
                double precioMaximo = resultados[1];
                double sumaPrecios = resultados[2];
                double cuentaProductos = resultados[3];
                double precioMedio = cuentaProductos > 0 ? sumaPrecios / cuentaProductos : 0; 

                // Formato de salida
                System.out.printf("Fabricante: %s, Precio mínimo: %.2f, Precio máximo: %.2f, Precio medio: %.2f%n",
                        fab.getNombre(), precioMinimo, precioMaximo, precioMedio);
            });

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€.
     * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
     */
    @Test
    void test40() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS
            //Tengo que filtrar fabricantes con precio medio superior a 200
            listFab.stream()

                    .filter(fab -> fab.getProductos().stream()
                            .mapToDouble(Producto::getPrecio)
                            .average()
                            .orElse(0) >= 200

                    )

                    .forEach(fab -> {

                        //Este objeto es más fácil que .reduce
                        DoubleSummaryStatistics stats = fab.getProductos().stream()

                                .mapToDouble(Producto::getPrecio)
                                .summaryStatistics(); // Resumen estadístico de los precios

                        // Obtener resultados
                        double precioMinimo = stats.getMin();
                        double precioMaximo = stats.getMax();
                        double precioMedio = stats.getAverage();
                        long totalProductos = stats.getCount();

                        // Mostrar resultados
                        System.out.printf("Fabricante %s: Precio mínimo: %.2f€, Precio máximo: %.2f€, Precio medio: %.2f€, Total de productos: %d%n",
                                fab.getIdFabricante(), precioMinimo, precioMaximo, precioMedio, totalProductos);
                    });

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
     */
    @Test
    void test41() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            System.out.println("Fabricantes con más 2 o más productos: ");

            //TODO STREAMS
            listFab.stream()

                    .filter(fabricante -> fabricante.getProductos().size() >= 2)
                    .forEach(fabricante -> System.out.println(fabricante.getNombre()));

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €.
     * Ordenado de mayor a menor número de productos.
     */
    @Test
    void test42() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
     */
    @Test
    void test43() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            System.out.println("Fabricantes con una suma de precios superior a 1000€");

            listFab.stream()

                    .filter(fabricante -> fabricante.getProductos().stream()

                            .mapToDouble(Producto::getPrecio)
                            .sum() > 1000
                    )

                    .forEach(fabricante -> System.out.println(fabricante.getNombre()));
                    ;

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
     * Ordenado de menor a mayor por cuantía de precio de los productos.
     */
    @Test
    void test44() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            System.out.println("Fabricantes con una suma de precios superior a 1000");


            listFab.stream()

                    .filter(fabricante -> fabricante.getProductos().stream()

                            .mapToDouble(Producto::getPrecio)
                            .sum() > 1000
                    )

                    .sorted(Comparator.comparingDouble(fabricante -> fabricante.getProductos().stream()

                            .mapToDouble(Producto::getPrecio)

                            .sum()) // Ordena por la suma total de precios de los productos
                    )

                    .forEach(fabricante -> {

                        double sumaTotal = fabricante.getProductos().stream()

                                .mapToDouble(Producto::getPrecio)

                                .sum(); // Calcula la suma total de precios

                        System.out.printf("Fabricante: %s - Suma total de precios: %.2f€%n", fabricante.getNombre(), sumaTotal);
                    });
            ;

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante.
     * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante.
     * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
     */
    @Test
    void test45() {

        try {
            fabricantesDAOImpl.beginTransaction();

            List<Fabricante> listFab = fabricantesDAOImpl.findAll();

            //TODO STREAMS

            listFab.stream()


                    .flatMap(fabricante -> fabricante.getProductos().stream()

                            .max(Comparator.comparing(Producto::getPrecio)) // Producto más caro

                            .stream() // Convertimos Optional en Stream

                            .map(producto -> new Object[]{ // Creamos un array con la información requerida
                                    producto.getNombre(),
                                    producto.getPrecio(),
                                    fabricante.getNombre()
                            })
                    )

                    // Ordeno por el nombre del fabricante

                    .sorted(Comparator.comparing(fabArray -> fabArray[2].toString())) // Comparador por el nombre del fabricante

                    .forEach(fabArray -> System.out.println(
                            "Producto: " + fabArray[0] + ", Precio: " + fabArray[1] + "€, Fabricante: " + fabArray[2]
                    ));

        } catch (RuntimeException e) {
            fabricantesDAOImpl.rollbackTransaction();
            throw e; // or display error message
        }
    }
}


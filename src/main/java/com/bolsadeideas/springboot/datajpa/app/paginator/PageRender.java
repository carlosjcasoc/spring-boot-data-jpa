package com.bolsadeideas.springboot.datajpa.app.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

  private final String url;
  private final Page<T> page;

  private final int totalPaginas;

  private final int numeroElementosPorPagina;

  private final int paginaActual;

  private final List<PageItem> paginas;

  public PageRender(String url, Page<T> page) {
    this.url = url;
    this.page = page;
    this.paginas = new ArrayList<>();

    this.numeroElementosPorPagina = page.getSize();
    this.totalPaginas = page.getTotalPages();
    this.paginaActual = page.getNumber() + 1;

    int desde, hasta;
    if (totalPaginas <= numeroElementosPorPagina) {
      desde = 1;
      hasta = totalPaginas;
    } else {
      if (paginaActual <= numeroElementosPorPagina / 2) {
        desde = 1;
        hasta = numeroElementosPorPagina;
      } else if (paginaActual >= totalPaginas - numeroElementosPorPagina / 2) {
        desde = totalPaginas - numeroElementosPorPagina + 1;
        hasta = numeroElementosPorPagina;
      } else {
        desde = paginaActual - numeroElementosPorPagina / 2;
        hasta = numeroElementosPorPagina;
      }
    }

    for (int i = 0; i < hasta; i++) {
      paginas.add(new PageItem(desde + i, paginaActual == desde + i));
    }
  }

    public String getUrl() {
        return url;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst() {
      return page.isFirst();
    }

    public boolean isLast() {
      return page.isLast();
    }

    public boolean isHasNext() {
      return page.hasNext();
    }

    public boolean isHasPrevious() {
      return page.hasPrevious();
    }
}

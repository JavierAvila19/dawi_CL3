package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Producto;
import com.example.demo.models.ProductoReport;
import com.example.demo.repositories.IProductoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private IProductoDao productoRepository;
	
	@Override
	public List<Producto> getAllProductos(){
		return this.productoRepository.findAll();
	}

	@Override
	public InputStream getReportProductos() throws Exception {
		try {
			List<Producto> listaProducto  = this.getAllProductos();
			List<ProductoReport> listaData = new ArrayList<ProductoReport>();
			listaData.add(new ProductoReport());
			listaData.get(0).setProductosList((listaProducto));
			JRBeanCollectionDataSource dts = new JRBeanCollectionDataSource(listaData);
			
			Map<String, Object> parameters = new HashMap<>();
			JasperReport jasperReportObj = getJasperReportCompiled();
			JasperPrint jPrint = JasperFillManager.fillReport(jasperReportObj, parameters, dts);
			InputStream result = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jPrint));
			return result;
		} catch(JRException ex) {
			throw ex;
		}
		
	}
	
	private JasperReport getJasperReportCompiled() {
		try {
			InputStream productoReportStream = getClass().getResourceAsStream("/jasper/productos_report.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(productoReportStream);
			JRSaver.saveObject(jasperReport, "productos_report.jasper");
			return jasperReport;
		} catch (Exception e) {
			return null;
		}
	}

	

}

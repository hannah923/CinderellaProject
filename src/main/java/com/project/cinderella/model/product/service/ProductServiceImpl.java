package com.project.cinderella.model.product.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.cinderella.common.FileManager;
import com.project.cinderella.exception.ProductRegistException;
import com.project.cinderella.model.domain.Color;
import com.project.cinderella.model.domain.Gender;
import com.project.cinderella.model.domain.Image;
import com.project.cinderella.model.domain.Product;
import com.project.cinderella.model.domain.Psize;
import com.project.cinderella.model.domain.Tag;
import com.project.cinderella.model.product.repository.ColorDAO;
import com.project.cinderella.model.product.repository.GenderDAO;
import com.project.cinderella.model.product.repository.ImageDAO;
import com.project.cinderella.model.product.repository.ProductDAO;
import com.project.cinderella.model.product.repository.PsizeDAO;
import com.project.cinderella.model.product.repository.TagDAO;


@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ImageDAO imageDAO;

	@Autowired
	private PsizeDAO psizeDAO;

	@Autowired
	private ColorDAO colorDAO;

	@Autowired
	private TagDAO tagDAO;

	@Autowired
	private GenderDAO genderDAO;

	@Override
	public List selectAll() {
		return productDAO.selectAll();
	}

	@Override
	public List selectById(int subcategory_id) {
		return productDAO.selectById(subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}

	@Override
	public void regist(FileManager fileManager, Product product) throws ProductRegistException {

		String ext = fileManager.getExtend(product.getRepImg().getOriginalFilename());
		product.setFilename(ext); // Ȯ���� ����
		// db�� �ִ� ���� DAO���� ��Ű��
		productDAO.insert(product);

		// ���� ���ε�!!�� FileManager���� ��Ų��
		// ��ǥ�̹��� ���ε�
		String basicImg = product.getProduct_id() + "." + ext;
		fileManager.saveFile(fileManager.getSaveBasicDir() + File.separator + basicImg, product.getRepImg());

		// �߰��̹��� ���ε� (FileManager���� �߰��̹��� ������ŭ ���ε� ������ ��Ű�� �ȴ�!!)
		for (int i = 0; i < product.getAddImg().length; i++) {

			MultipartFile file = product.getAddImg()[i];
			ext = fileManager.getExtend(file.getOriginalFilename());

			// image table�� �ֱ�!!
			Image image = new Image();
			image.setProduct_id(product.getProduct_id()); // fk
			image.setFilename(ext); // Ȯ���� �ֱ�
			imageDAO.insert(image);

			// �޴����� ���� �޼��� ȣ��
			String addImg = image.getImage_id() + "." + ext;
			fileManager.saveFile(fileManager.getSaveAddonDir() + File.separator + addImg, file);
		}

		// ��Ÿ �ɼ� ��, ���� ������ �ֱ� (�ݺ�������...)

		// ������
		for (Psize psize : product.getPsize()) {
			logger.debug("����� ������ ������� " + psize.getFit());
			psize.setProduct_id(product.getProduct_id());// fk ����
			psizeDAO.insert(psize);
		}

		// ����
		for (Color color : product.getColor()) {
			logger.debug("�Ѱܹ��� ������ " + color.getPicker());
			color.setProduct_id(product.getProduct_id());
			colorDAO.insert(color);
		}

		// �±�
		for (Tag tag : product.getPtag()) {
			logger.debug("�Ѱܹ��� �±״� " + tag.getTag_name());
			tag.setProduct_id(product.getProduct_id());
			tagDAO.insert(tag);
		}

		// �±�
		for (Gender gender: product.getPgender()) {
			logger.debug("�Ѱܹ��� ������ " + gender.getGender_name());
			gender.setProduct_id(product.getProduct_id());
			genderDAO.insert(gender);
		}

	}

	@Override
	public void update(Product product) {
		productDAO.update(product);
	}

	@Override
	public void delete(int product_id) {
		// TODO Auto-generated method stub

	}

}

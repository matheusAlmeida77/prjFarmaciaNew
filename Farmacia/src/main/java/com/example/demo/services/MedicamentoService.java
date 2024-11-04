package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Medicamento;
import com.example.demo.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {
	private final MedicamentoRepository medicamentoRepository;
	
	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentoRepository) {
		this.medicamentoRepository = medicamentoRepository;
	}
	
	public Medicamento saveMedicamento(Medicamento medicamento) {
		return medicamentoRepository.save(medicamento);
	}
	
	public Medicamento getMedicamentoById(Long id) {
		return medicamentoRepository.findById(id).orElse(null);
	}
	
	public List<Medicamento> getAllMedicamentos(){
		return medicamentoRepository.findAll();
	}
	
	public void deleteMedicamentoById(Long id) {
		medicamentoRepository.deleteById(id);
	}
	
	public Medicamento updateMedicamento(Long id, Medicamento updatedMedicamento) {
		Optional<Medicamento> existentMedicamento = medicamentoRepository.findById(id);
		if (existentMedicamento.isPresent()){
			Medicamento medicamento = existentMedicamento.get();
			medicamento.setNome(updatedMedicamento.getNome());
			medicamento.setBula(updatedMedicamento.getBula());
			medicamento.setIdFornecedor(updatedMedicamento.getIdFornecedor());
			medicamento.setDataValidade(updatedMedicamento.getDataValidade());
			return medicamentoRepository.save(medicamento);
		} else {
			return null;
		}
	}
}
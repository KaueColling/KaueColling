package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.service.implementation.PetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetServiceImpl service; //todios os fnals é required
    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody PetPostRequestDTO dto){
        return ResponseEntity.ok(service.postPet(dto));

    }
    @GetMapping("{id}")
    public ResponseEntity<List<Pet>> getPetsByClient(@PathVariable Long id){
        return new ResponseEntity<>(service.getAllPetsByClient(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PetGetRequestDTO>> getPets(){
        return ResponseEntity.ok(service.getPets());
    }
    @PatchMapping
    public ResponseEntity<Pet> patchPet(@RequestBody PetPatchRequestDTO dto){
        return ResponseEntity.ok(service.patchPet(dto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id){
        return ResponseEntity.ok(service.deletePet(id));
    }


}

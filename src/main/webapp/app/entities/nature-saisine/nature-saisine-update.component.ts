import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INatureSaisine, NatureSaisine } from 'app/shared/model/nature-saisine.model';
import { NatureSaisineService } from './nature-saisine.service';

@Component({
  selector: 'jhi-nature-saisine-update',
  templateUrl: './nature-saisine-update.component.html',
})
export class NatureSaisineUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(protected natureSaisineService: NatureSaisineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureSaisine }) => {
      this.updateForm(natureSaisine);
    });
  }

  updateForm(natureSaisine: INatureSaisine): void {
    this.editForm.patchValue({
      id: natureSaisine.id,
      nom: natureSaisine.nom,
      slug: natureSaisine.slug,
      description: natureSaisine.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const natureSaisine = this.createFromForm();
    if (natureSaisine.id !== undefined) {
      this.subscribeToSaveResponse(this.natureSaisineService.update(natureSaisine));
    } else {
      this.subscribeToSaveResponse(this.natureSaisineService.create(natureSaisine));
    }
  }

  private createFromForm(): INatureSaisine {
    return {
      ...new NatureSaisine(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INatureSaisine>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

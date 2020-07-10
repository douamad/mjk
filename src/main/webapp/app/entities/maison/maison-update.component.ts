import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMaison, Maison } from 'app/shared/model/maison.model';
import { MaisonService } from './maison.service';

@Component({
  selector: 'jhi-maison-update',
  templateUrl: './maison-update.component.html',
})
export class MaisonUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(protected maisonService: MaisonService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maison }) => {
      this.updateForm(maison);
    });
  }

  updateForm(maison: IMaison): void {
    this.editForm.patchValue({
      id: maison.id,
      nom: maison.nom,
      slug: maison.slug,
      description: maison.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const maison = this.createFromForm();
    if (maison.id !== undefined) {
      this.subscribeToSaveResponse(this.maisonService.update(maison));
    } else {
      this.subscribeToSaveResponse(this.maisonService.create(maison));
    }
  }

  private createFromForm(): IMaison {
    return {
      ...new Maison(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaison>>): void {
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

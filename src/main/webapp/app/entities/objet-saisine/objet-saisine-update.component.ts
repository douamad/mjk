import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IObjetSaisine, ObjetSaisine } from 'app/shared/model/objet-saisine.model';
import { ObjetSaisineService } from './objet-saisine.service';

@Component({
  selector: 'jhi-objet-saisine-update',
  templateUrl: './objet-saisine-update.component.html',
})
export class ObjetSaisineUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(protected objetSaisineService: ObjetSaisineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objetSaisine }) => {
      this.updateForm(objetSaisine);
    });
  }

  updateForm(objetSaisine: IObjetSaisine): void {
    this.editForm.patchValue({
      id: objetSaisine.id,
      nom: objetSaisine.nom,
      slug: objetSaisine.slug,
      description: objetSaisine.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const objetSaisine = this.createFromForm();
    if (objetSaisine.id !== undefined) {
      this.subscribeToSaveResponse(this.objetSaisineService.update(objetSaisine));
    } else {
      this.subscribeToSaveResponse(this.objetSaisineService.create(objetSaisine));
    }
  }

  private createFromForm(): IObjetSaisine {
    return {
      ...new ObjetSaisine(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IObjetSaisine>>): void {
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

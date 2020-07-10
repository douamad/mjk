import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrigineSaisine, OrigineSaisine } from 'app/shared/model/origine-saisine.model';
import { OrigineSaisineService } from './origine-saisine.service';

@Component({
  selector: 'jhi-origine-saisine-update',
  templateUrl: './origine-saisine-update.component.html',
})
export class OrigineSaisineUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(protected origineSaisineService: OrigineSaisineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ origineSaisine }) => {
      this.updateForm(origineSaisine);
    });
  }

  updateForm(origineSaisine: IOrigineSaisine): void {
    this.editForm.patchValue({
      id: origineSaisine.id,
      nom: origineSaisine.nom,
      slug: origineSaisine.slug,
      description: origineSaisine.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const origineSaisine = this.createFromForm();
    if (origineSaisine.id !== undefined) {
      this.subscribeToSaveResponse(this.origineSaisineService.update(origineSaisine));
    } else {
      this.subscribeToSaveResponse(this.origineSaisineService.create(origineSaisine));
    }
  }

  private createFromForm(): IOrigineSaisine {
    return {
      ...new OrigineSaisine(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrigineSaisine>>): void {
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

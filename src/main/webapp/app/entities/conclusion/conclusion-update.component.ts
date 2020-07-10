import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConclusion, Conclusion } from 'app/shared/model/conclusion.model';
import { ConclusionService } from './conclusion.service';

@Component({
  selector: 'jhi-conclusion-update',
  templateUrl: './conclusion-update.component.html',
})
export class ConclusionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(protected conclusionService: ConclusionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conclusion }) => {
      this.updateForm(conclusion);
    });
  }

  updateForm(conclusion: IConclusion): void {
    this.editForm.patchValue({
      id: conclusion.id,
      nom: conclusion.nom,
      slug: conclusion.slug,
      description: conclusion.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const conclusion = this.createFromForm();
    if (conclusion.id !== undefined) {
      this.subscribeToSaveResponse(this.conclusionService.update(conclusion));
    } else {
      this.subscribeToSaveResponse(this.conclusionService.create(conclusion));
    }
  }

  private createFromForm(): IConclusion {
    return {
      ...new Conclusion(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConclusion>>): void {
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

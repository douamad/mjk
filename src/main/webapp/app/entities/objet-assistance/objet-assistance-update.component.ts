import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IObjetAssistance, ObjetAssistance } from 'app/shared/model/objet-assistance.model';
import { ObjetAssistanceService } from './objet-assistance.service';

@Component({
  selector: 'jhi-objet-assistance-update',
  templateUrl: './objet-assistance-update.component.html',
})
export class ObjetAssistanceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(
    protected objetAssistanceService: ObjetAssistanceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objetAssistance }) => {
      this.updateForm(objetAssistance);
    });
  }

  updateForm(objetAssistance: IObjetAssistance): void {
    this.editForm.patchValue({
      id: objetAssistance.id,
      nom: objetAssistance.nom,
      slug: objetAssistance.slug,
      description: objetAssistance.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const objetAssistance = this.createFromForm();
    if (objetAssistance.id !== undefined) {
      this.subscribeToSaveResponse(this.objetAssistanceService.update(objetAssistance));
    } else {
      this.subscribeToSaveResponse(this.objetAssistanceService.create(objetAssistance));
    }
  }

  private createFromForm(): IObjetAssistance {
    return {
      ...new ObjetAssistance(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IObjetAssistance>>): void {
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

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEthnie, Ethnie } from 'app/shared/model/ethnie.model';
import { EthnieService } from './ethnie.service';

@Component({
  selector: 'jhi-ethnie-update',
  templateUrl: './ethnie-update.component.html',
})
export class EthnieUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    slug: [],
    description: [],
  });

  constructor(protected ethnieService: EthnieService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ethnie }) => {
      this.updateForm(ethnie);
    });
  }

  updateForm(ethnie: IEthnie): void {
    this.editForm.patchValue({
      id: ethnie.id,
      nom: ethnie.nom,
      slug: ethnie.slug,
      description: ethnie.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ethnie = this.createFromForm();
    if (ethnie.id !== undefined) {
      this.subscribeToSaveResponse(this.ethnieService.update(ethnie));
    } else {
      this.subscribeToSaveResponse(this.ethnieService.create(ethnie));
    }
  }

  private createFromForm(): IEthnie {
    return {
      ...new Ethnie(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEthnie>>): void {
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

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRequerant, Requerant } from 'app/shared/model/requerant.model';
import { RequerantService } from './requerant.service';
import { IProfession } from 'app/shared/model/profession.model';
import { ProfessionService } from 'app/entities/profession/profession.service';
import { IEthnie } from 'app/shared/model/ethnie.model';
import { EthnieService } from 'app/entities/ethnie/ethnie.service';

type SelectableEntity = IProfession | IEthnie;

@Component({
  selector: 'jhi-requerant-update',
  templateUrl: './requerant-update.component.html',
})
export class RequerantUpdateComponent implements OnInit {
  isSaving = false;
  professions: IProfession[] = [];
  ethnies: IEthnie[] = [];

  editForm = this.fb.group({
    id: [],
    prenom: [],
    nom: [],
    telephone: [],
    mail: [],
    localite: [],
    genre: [],
    age: [],
    professionId: [],
    ethnieId: [],
  });

  constructor(
    protected requerantService: RequerantService,
    protected professionService: ProfessionService,
    protected ethnieService: EthnieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requerant }) => {
      this.updateForm(requerant);

      this.professionService.query().subscribe((res: HttpResponse<IProfession[]>) => (this.professions = res.body || []));

      this.ethnieService.query().subscribe((res: HttpResponse<IEthnie[]>) => (this.ethnies = res.body || []));
    });
  }

  updateForm(requerant: IRequerant): void {
    this.editForm.patchValue({
      id: requerant.id,
      prenom: requerant.prenom,
      nom: requerant.nom,
      telephone: requerant.telephone,
      mail: requerant.mail,
      localite: requerant.localite,
      genre: requerant.genre,
      age: requerant.age,
      professionId: requerant.professionId,
      ethnieId: requerant.ethnieId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requerant = this.createFromForm();
    if (requerant.id !== undefined) {
      this.subscribeToSaveResponse(this.requerantService.update(requerant));
    } else {
      this.subscribeToSaveResponse(this.requerantService.create(requerant));
    }
  }

  private createFromForm(): IRequerant {
    return {
      ...new Requerant(),
      id: this.editForm.get(['id'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      mail: this.editForm.get(['mail'])!.value,
      localite: this.editForm.get(['localite'])!.value,
      genre: this.editForm.get(['genre'])!.value,
      age: this.editForm.get(['age'])!.value,
      professionId: this.editForm.get(['professionId'])!.value,
      ethnieId: this.editForm.get(['ethnieId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequerant>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
